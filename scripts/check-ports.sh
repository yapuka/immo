#!/usr/bin/env bash
set -u

PORTS=(27017 8080 8081 5173 80)
FOUND=0

for port in "${PORTS[@]}"; do
  if command -v lsof >/dev/null 2>&1; then
    info=$(lsof -nP -iTCP:"$port" -sTCP:LISTEN 2>/dev/null || true)
    if [[ -n "$info" ]]; then
      FOUND=1
      echo "PORT $port IN USE"
      echo "$info"
      echo "---"
    else
      echo "PORT $port FREE"
    fi
  else
    echo "lsof is not installed; cannot check ports on this system."
    exit 127
  fi
done

if [[ "$FOUND" -eq 1 ]]; then
  echo "At least one project port is occupied. Stop the process or run: npm run ports:free"
  exit 1
fi

echo "All expected development ports are free."
exit 0
