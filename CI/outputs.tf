output "environment_url" {
  description = "Public URL of the EB environment"
  value        = aws_elastic_beanstalk_environment.env.endpoint_url
}

output "artifact_bucket" {
  description = "S3 bucket for application bundles"
  value       =   aws_s3_bucket.deploy.bucket
}

output "service_role_arn" {
  value = aws_iam_role.service.arn
}
