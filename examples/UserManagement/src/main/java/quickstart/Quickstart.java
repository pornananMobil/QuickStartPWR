/*
 * Copyright 2017 Okta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package quickstart;

import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.ClientBuilder;
import com.okta.sdk.client.Clients;
import com.okta.sdk.resource.group.GroupBuilder;
import com.okta.sdk.resource.ResourceException;
import com.okta.sdk.resource.user.UserBuilder;
import com.okta.sdk.resource.group.GroupList;

import com.okta.sdk.resource.group.Group;
import com.okta.sdk.resource.user.User;
import com.okta.sdk.resource.user.UserList;
import com.okta.sdk.resource.user.UserStatus;

import java.util.UUID;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/**
 * This class demonstrates the code found in the Okta Java SDK QuickStart Guide
 *
 * @since 0.5.0
 */
@SuppressWarnings("PMD.UnusedLocalVariable")
public class Quickstart {

    public static void main(String[] args) {

        String email = "joe.coder+" + UUID.randomUUID().toString() + "@example.com";
        String groupName = "java-sdk-quickstart-" + UUID.randomUUID().toString();
        String groupDescription = "java-sdk-quickstart-" + UUID.randomUUID().toString();
        String firstname = "java-sdk-quickstart-" + UUID.randomUUID().toString();
        String lastname = "java-sdk-quickstart-" + UUID.randomUUID().toString();

        final char[] password = {'P', 'a', 's', 's', 'w', 'o', 'r', 'd', '1'};
        Scanner scanner = new Scanner(System.in);

        ClientBuilder builder;
        Client client;
        Group group = null;
        User user = null;
        String userID = null;
        String groupID = null;

        // Instantiate a builder for your Client. If needed, settings like Proxy and Caching can be defined here.
        builder = Clients.builder()
            .setOrgUrl("https://xom-poc.okta.com")  // e.g. https://dev-123456.okta.com
            .setClientCredentials(
                new TokenClientCredentials("00Bgd232898fpWTQlOp2HSjNntkjA0OzxGkGUQKR2e"));

        // No need to define anything else; build the Client instance. The ClientCredential information will be automatically found
        // in pre-defined locations: i.e. ~/.okta/okta.yaml
        client = builder.build();
        String quit = "N";
        String temp = null;


        while(quit.equals("N"))
        {
            try {

                System.out.println("Please Select Option!");
                System.out.println("1.) List Group");
                System.out.println("2.) Create Groups");
                System.out.println("3.) Create User");
                System.out.println("4.) List User");
                System.out.println("5.) Delete User");
                System.out.println("6.) Delete Group");
                System.out.println("7.) List user in Group (3rdParty)");
                System.out.println("8.) Search User (name , email)");
                System.out.println("9.) Create Dummy group");
                System.out.println("10.) Create user with group");
                System.out.println("11.) Search Group");

                temp = scanner.nextLine();

                if (temp.equals("1")){
                    GroupList groupList = client.listGroups();

                    groupList.stream()
                        .forEach(g -> {
                            printGroup(g);
                        });

                }else if (temp.equals("2")){
                    System.out.println("Please enter groupName");
                    groupName = scanner.nextLine();
                    System.out.println("Please enter Desciption");
                    groupDescription = scanner.nextLine();

                    group = GroupBuilder.instance()
                        .setName(groupName)
                        .setDescription(groupDescription)
                        .buildAndCreate(client);
                    System.out.println("Group is created!!");
                    printGroup(group);

                }else if(temp.equals("3")){
                    System.out.println("Please enter Email");
                    email = scanner.nextLine();
                    System.out.println("Please enter setFirstName");
                    firstname  = scanner.nextLine();
                    System.out.println("Please enter setLastName");
                    lastname  = scanner.nextLine();

                    user = UserBuilder.instance()
                        .setEmail(UUID.randomUUID().toString() +"_"+ email )
                        .setFirstName(firstname)
                        .setLastName(lastname)
                        .setPassword(password)
                        .setSecurityQuestion("Favorite security question?")
                        .setSecurityQuestionAnswer("None of them!")
                        .buildAndCreate(client);
                    System.out.println("User is created!!");
                    printUser(user);

                }else if(temp.equals("4")){
                    UserList users = client.listUsers();

                    // stream
                    client.listUsers().stream()
                        .forEach(u -> {
                            printUser(u);
                        });
                }else if (temp.equals("5")){
                    System.out.println("Please enter USER ID to be deleted , you can get it from List user ");
                     userID= scanner.nextLine();
                     user = client.getUser(userID);
                     user.deactivate();
                     user.delete();
                }else if (temp.equals("6")) {
                    System.out.println("Please enter Group ID to be deleted , you can get it from List Group ");
                    groupID= scanner.nextLine();
                    group = client.getGroup(groupID);
                    group.delete();
                } else if (temp.equals("7")) {
                    System.out.println("Please enter Group ID to be deleted , you can get it from List Group ");
                    groupID= scanner.nextLine();
                    group = client.getGroup(groupID);
                    UserList userList = group.listUsers();

                    userList.stream()
                        .forEach(u -> {
                            printUser(u);
                        });

                }else if (temp.equals("8")){
                    System.out.println("Please enter name or email ");
                    String q = scanner.nextLine();
                    UserList userList = client.listUsers(q, null, null, null, null);

                    userList.stream()
                        .forEach(u -> {
                            printUser(u);
                        });

                }else if (temp.equals("9")){
                    System.out.println("Please enter groupName");
                    groupName = scanner.nextLine();
                    System.out.println("Please enter Desciption");
                    groupDescription = scanner.nextLine();

                    System.out.println("Please enter How many dummy group you want to create ");
                    int q = scanner.nextInt();

                    for (int i=0;i<q;i++) {
                        group = GroupBuilder.instance()
                            .setName(groupName + "_" + i)
                            .setDescription(groupDescription + "_" + i )
                            .buildAndCreate(client);
                        printGroup(group);
//                        TimeUnit.SECONDS.sleep(2);

                    }
                }else if(temp.equals("10")){
                    System.out.println("Please enter Email");
                    email = scanner.nextLine();
                    System.out.println("Please enter setFirstName");
                    firstname  = scanner.nextLine();
                    System.out.println("Please enter setLastName");
                    lastname  = scanner.nextLine();
                    System.out.println("Please enter Group ID");
                    String groupId  = scanner.nextLine();

                    user = UserBuilder.instance()
                        .setEmail(UUID.randomUUID().toString() +"_"+ email )
                        .setFirstName(firstname)
                        .setLastName(lastname)
                        .setPassword(password)
                        .setSecurityQuestion("Favorite security question?")
                        .setSecurityQuestionAnswer("None of them!")
                        .setGroups(new HashSet<>(Arrays.asList(groupId)))
                        .buildAndCreate(client);
                    System.out.println("User is created!!");
                    printUser(user);

                }else if (temp.equals("11")){
                    System.out.println("Please enter Group name");
                    groupName  = scanner.nextLine();

                    GroupList groupList = client.listGroups(groupName,null,null);

                    groupList.stream()
                        .forEach(g -> {
                            printGroup(g);
                        });

                }


/*
                System.out.print("Enter your Group name ? ");
                groupName = scanner.nextLine();
                // Create a group
                group = GroupBuilder.instance()
                    .setName(groupName)
                    .setDescription("Quickstart created Group")
                    .buildAndCreate(client);

                println("Group: '" + group.getId() + "' was last updated on: "
                    + group.getLastUpdated());

                // Create a User Account
                user = UserBuilder.instance()
                    .setEmail(email)
                    .setFirstName("Joe")
                    .setLastName("Coder")
                    .setPassword(password)
                    .setSecurityQuestion("Favorite security question?")
                    .setSecurityQuestionAnswer("None of them!")
                    .putProfileProperty("division",
                        "Seven") // key/value pairs predefined in the user profile schema
                    .setActive(true)
                    .buildAndCreate(client);

                // add user to the newly created group
                user.addToGroup(group.getId());

                String userId = user.getId();
                println("User created with ID: " + userId);

                // You can look up user by ID
                println("User lookup by ID: " + client.getUser(userId).getProfile().getLogin());

                // or by Email
                println("User lookup by Email: " + client.getUser(email).getProfile().getLogin());

                // get the list of users
                UserList users = client.listUsers();

                // get the first user in the collection
                println(
                    "First user in collection: " + users.iterator().next().getProfile().getEmail());

                // or loop through all of them (paging is automatic)
//            int ii = 0;
//            for (User tmpUser : users) {
//                println("["+ ii++ +"] User: " + tmpUser.getProfile().getEmail());
//            }
*/
            } catch (ResourceException e) {

                // we can get the user friendly message from the Exception
                println(e.getMessage());

                // and you can get the details too
                e.getCauses().forEach(cause -> println("\t" + cause.getSummary()));
                throw e;
       //     } catch (InterruptedException e) {
        //        System.err.format("IOException: %s%n", e);
            }
            finally {

            }
            System.out.print("Quit ? (Y/N) ");

            quit = scanner.nextLine();
        }
    }



    private static void printUser(User user) {
        System.out.println("Login: " + user.getProfile().getLogin() + " ID: " + user.getId() + "' was last updated on: "  + user.getLastUpdated());
        System.out.println("Belong to groups");

        GroupList groups = user.listGroups();
        groups.stream()
            .forEach(g -> {
                printGroup(g);
            });
    }



    private static void printGroup(Group group) {
        System.out.println("Name: " + group.getProfile().getName()+ " , ID : " + group.getId()+ "' was last updated on: " + group.getLastUpdated());
    }


    private static void println(String message) {
        System.out.println(message);
        System.out.flush();
    }



}
