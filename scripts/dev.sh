#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT_DIR"

echo "Starting MongoDB and Mongo Express..."
docker compose up -d mongodb mongo-express

echo "Starting backend and frontend..."
exec npx concurrently \
  -n backend,frontend \
  --prefix-colors blue,green \
  "npm run dev:backend" \
  "npm run dev:frontend"
