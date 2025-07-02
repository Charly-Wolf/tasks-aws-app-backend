#!/bin/bash
set -e # Exit on any error

echo "Initializing Terraform..."
cd CI || { echo "CI directory not found"; exit 1; }
terraform fmt -check -recursive || echo "⚠️ Terraform format check failed, continuing anyway..."

echo "Running 'terraform init' with backend config..."
terraform init -upgrade

echo "✅ Init complete!"