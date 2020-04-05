package com.nerdysoft.taskmanager.service;

import com.nerdysoft.taskmanager.dto.User;
import com.nerdysoft.taskmanager.dto.Registrant;

public interface IUserRegistrationService {

    User registerNewUserAccount(Registrant registrant);

}
