#!/usr/bin/env zsh

rm -rf out
mkdir -p out
bun run copyfiles.ts src/**/*.java
bun run copyfiles.ts bin/**/*.class

cd out
jar cfe out.jar main.Main . ../*.txt
mv out.jar ../2110215_Lab1_2022_2_6532068721_Nutthapat.jar
