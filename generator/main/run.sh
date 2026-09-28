#!/usr/bin/env sh
set -eu

# Always run relative to this script, regardless of the caller's directory.
SCRIPT_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
cd "$SCRIPT_DIR"

echo "Building Dataset Generator with Maven..."
mvn clean package

exec java -Xmx6144m -jar "target/dataset-generator.jar" "$@"
