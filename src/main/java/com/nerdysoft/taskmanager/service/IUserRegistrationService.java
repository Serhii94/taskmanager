package com.nerdysoft.taskmanager.service;

import com.nerdysoft.taskmanager.domain.User;
import com.nerdysoft.taskmanager.dto.Registrant;

public interface IUserRegistrationService {

    User registerNewUserAccount(Registrant registrant);

}
