#!/bin/bash
cp -r ../app .
docker build -f Dockerfile-frontend -t nostalgiatune/k8s-frontend:1.0.0 .
docker build -f Dockerfile-backend -t nostalgiatune/k8s-backend:1.0.0 .
rm -rf app
