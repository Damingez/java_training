package pl.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitubTests {

  @Test
  public void testCommits () throws IOException {
    Github github = new RtGithub("1f585c1fe720cc1e81442eb3e7e06ce975cce9ee");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("Damingez", "java_training")).commits();

    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
      }

    }
  }


