package com.code.onboarding.service;

import java.util.List;

public interface MailNotificationService {

  void sendNotificationForOnboardingInitiation(List<String> recipients);
}
