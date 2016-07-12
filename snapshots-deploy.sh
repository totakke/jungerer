#!/bin/bash

set -e

if head -n 1 project.clj | grep -e '-SNAPSHOT' >/dev/null; then
    lein deploy snapshots
fi
