/// <reference types="bun-types" />

import { exec } from "node:child_process";

const files = process.argv.slice(2);

for (const file of files) {
  const body = file.replace("src/", "").replace("bin/", "");

  exec(
    `mkdir -p out/${body
      .split("/")
      .slice(0, -1)
      .join("/")} && cp ${file} out/${body}`,
    (stderr, stdout) => {
      stderr && console.log(stderr);
    }
  );
}
