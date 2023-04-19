#!/usr/bin/env zsh

rm -rf out
mkdir -p out
bun run copyfiles.ts src/**/*.java
bun run copyfiles.ts test/**/*.java
bun run copyfiles.ts bin/**/*.class

cd out
jar cfe out.jar application.Main .
mv out.jar ../Lab3_2022_2_6532068721.jar
