package ru.mentee.power;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions;

class MenteeProgressTest {
  @Test
  void shouldFormatSummaryWhenProgressCreated() {
    MenteeProgress progress = new MenteeProgress("Петя", 1, 4);

    String result = progress.summary();

    assertThat(result).isEqualTo("Sprint 1 - Петя: planned 4 h");
  }

  @Test
  void shouldDetectReadinessWhenHoursAboveThreshold() {
    MenteeProgress progress = new MenteeProgress("Петя", 1, 4);
    assertThat(progress.readyForSprint()).isTrue();
  }

  @Test
  void shouldDetectReadinessWhenHoursBelowThreshold() {
    MenteeProgress progress = new MenteeProgress("Петя", 1, 2);
    assertThat(progress.readyForSprint()).isFalse();
  }

}