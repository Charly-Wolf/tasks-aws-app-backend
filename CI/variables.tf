variable "region" {
  type        = string
  description = "Default AWS region."
  default     = "us-east-1"
}

variable "app_name" {
  description = "Elastic Beanstalk application name"
  type        = string
  default     = "tasks-app-demo-from-terraform"
}

variable "env_name" {
  description = "Elastic Beanstalk environment name"
  type        = string
  default     = "tasks-from-terraform-dev"
}

variable "platform" {
  description = "EB platform/solution stack"
  type        = string
  default     = "64bit Amazon Linux 2023 v5.7.0 running Tomcat 10 Corretto 21"
}

variable "instance_type" {
  description = "EC2 type (free‑tier‑eligible)"
  type        = string
  default     = "t2.micro"
}

variable "mongo_uri" {
  description = "`MONGO_URI` for Spring Boot"
  type        = string
}

variable "tags" {
  description = "Common tags"
  type        = map(string)
  default     = {
    project = "tasks-terraform-demo"
  }
}
