**Task description:**

Implement task manager with the following functionality:

1. Register in a system;
2. Login into the system;
3. Add new task;
4. Edit task;
5. Delete task;
6. Share the task (share using email of another user in the system who will be also able to manage task afterward);
7. See list of tasks (list should auto-update when someone shares tasks with user; in the list, user should see who has shared task).

All the features should be implemented.

For the back-end position use Java with MongoDb.


**Designed Endpoints Identification Table**

| **#** | **endpoint**                   | **description**                            | **admin access only** |         
| :---: | ------------------------------ | ------------------------------------------ | :-------------------: |
|   1   | /tasks                         | access to the task collection resource     |    for GET method     |
|   2   | /tasks/{taskId}                | access to an individual task resource      |                       |
|   3   | /tasks/set                     | access to a set of specific task resources |                       |
|   4   | /tasks/share/{taskId}          | share a task resource                      |                       | 
|   5   | /users                         | access to the user collection resource     |    for GET method     |
|   6   | /users/{userId}                | access to an individual user resource      |                       |
|   7   | /users/registration            | registering of new users in the system     |                       |


