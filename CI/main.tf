provider "aws" {
  region = var.region
}

############################
# 1) S3 bucket for artifacts
############################
resource "random_id" "suffix" {
  byte_length = 4
}

resource "aws_s3_bucket" "deploy" {
  bucket = "${var.app_name}-${random_id.suffix.hex}"
  tags   = var.tags
}

############################
# 2) IAM – service & EC2 roles
############################
data "aws_iam_policy" "eb_enhanced_health" {
  arn = "arn:aws:iam::aws:policy/service-role/AWSElasticBeanstalkEnhancedHealth"
}

resource "aws_iam_role" "service" {
  name = "${var.app_name}-eb-service-role"
  assume_role_policy = jsonencode({
    Version : "2012-10-17",
    Statement : [{
      Effect : "Allow",
      Principal : { Service : "elasticbeanstalk.amazonaws.com" },
      Action : "sts:AssumeRole"
    }]
  })
  tags = var.tags
}

resource "aws_iam_role_policy_attachment" "service_attach" {
  role       = aws_iam_role.service.name
  policy_arn = data.aws_iam_policy.eb_enhanced_health.arn
}

resource "aws_iam_role" "ec2" {
  name = "${var.app_name}-eb-ec2-role"
  assume_role_policy = jsonencode({
    Version : "2012-10-17",
    Statement : [{
      Effect : "Allow",
      Principal : { Service : "ec2.amazonaws.com" },
      Action : "sts:AssumeRole"
    }]
  })
  tags = var.tags
}

# Attach baseline web‑tier policy (managed)
resource "aws_iam_role_policy_attachment" "ec2_attach_webtier" {
  role       = aws_iam_role.ec2.name
  policy_arn = "arn:aws:iam::aws:policy/AWSElasticBeanstalkWebTier"
}

resource "aws_iam_instance_profile" "ec2_profile" {
  name = "${var.app_name}-eb-ec2-profile"
  role = aws_iam_role.ec2.name
}

############################
# 3) Elastic Beanstalk
############################
resource "aws_elastic_beanstalk_application" "app" {
  name        = var.app_name
  description = "Spring Boot demo running on EB"
  tags        = var.tags
}

resource "aws_elastic_beanstalk_environment" "env" {
  name                = var.env_name
  application         = aws_elastic_beanstalk_application.app.name
  solution_stack_name = var.platform

  tags = var.tags

  # --- Single‑instance, free‑tier‑friendly ---
  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "EnvironmentType"
    value     = "SingleInstance"
  }

  # --- EC2 details ---
  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "InstanceType"
    value     = var.instance_type
  }

  # --- Connect our instance profile & service role ---
  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "IamInstanceProfile"
    value     = aws_iam_instance_profile.ec2_profile.name
  }

  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "ServiceRole"
    value     = aws_iam_role.service.arn
  }

  # --- App env vars ---
  setting {
    namespace = "aws:elasticbeanstalk:application:environment"
    name      = "MONGO_URI"
    value     = var.mongo_uri
  }
}
