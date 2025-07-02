terraform {
  required_version = ">= 1.12.2"

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }

  ### —STATE BACKEND —
  backend "s3" {
    bucket = "cardp-terraform-state-bucket"
    key    = "elastic‑beanstalk/terraform.tfstate"
    region = "us-east-1"
    encrypt = true
  }
}
