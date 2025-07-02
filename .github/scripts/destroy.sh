#!/bin/bash
set -euo pipefail

# Change to CI directory where Terraform configs are
cd CI || { echo "❌ CI directory not found"; exit 1; }

# Load Terraform outputs (bucket name, etc)
BUCKET_NAME=$(terraform output -raw artifact_bucket || true)

echo "🔍 Checking bucket: $BUCKET_NAME"

# Empty the S3 bucket first (required before deleting bucket)
if [[ -n "$BUCKET_NAME" ]]; then
  echo "🧹 Emptying S3 bucket: $BUCKET_NAME"
  aws s3 rm "s3://$BUCKET_NAME" --recursive
else
  echo "⚠️  No bucket name found from terraform output. Skipping bucket empty step."
fi

# Now safely destroy all Terraform-managed infrastructure
echo "⛔️ Running 'terraform destroy' (auto-approve)..."
terraform destroy -auto-approve

echo "✅ All resources destroyed."
