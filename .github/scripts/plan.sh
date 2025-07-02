#!/bin/bash
set -e # Exit on any error

echo "Running Terraform plan..."
cd CI || { echo "CI directory not found"; exit 1; }
terraform validate

terraform plan \
  -out="plan.tfplan"

echo "✅ Plan saved to plan.tfplan"