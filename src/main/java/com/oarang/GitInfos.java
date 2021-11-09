package com.oarang;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


public class GitInfos extends jsonBase{
    private String[] info_strings = {  "git.branch", "git.build.host", "git.build.time",
            "git.build.user.email", "git.build.user.name" , "git.build.version",
            "git.closest.tag.commit.count", "git.closest.tag.name" , "git.commit.id",
            "git.commit.id.abbrev", "git.commit.id.describe", "git.commit.id.describe-short",
            "git.commit.message.full" , "git.commit.message.short",
            "git.commit.time", "git.commit.user.email", "git.commit.user.name", "git.dirty",
            "git.remote.origin.url", "git.tags"};
    public HashMap<String, String> info = new HashMap<String, String>();
    private String allInfos = new String();

    public GitInfos() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("git.properties");
        try {
            readFromInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        allInfos = resultStringBuilder.toString();
        parseGitInfos(allInfos);
    }

    private void parseGitInfos(String infos) {
        JSONObject jsonObject = new JSONObject(infos);
        parseJson(info, jsonObject, info_strings);
    }

    public String getBuildTime() {
        return info.get("git.build.time");
    }

    public String getBranch() {
        return info.get("git.branch");
    }

    public String getCommitIDDesc(){
        return info.get("git.commit.id.describe");
    }

    public String getBuildUserEmail(){
        return info.get("git.build.user.email");
    }

    public String getAllInfos() {
        return allInfos;
    }
}
