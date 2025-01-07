package dev.ivangg.rovly.lib.clerk;

import dev.ivangg.rovly.lib.clerk.model.ClerkUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClerkApiClientTester {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting up");

        ClerkApiClient client = new ClerkApiClient("Secret Key");

        List<String> emailList = new ArrayList<>();
        emailList.add("user email");

        List<ClerkUser> clerkUsers = client.listUsers(emailList);
        System.out.println("USER LIST: " + clerkUsers);

        ClerkUser clerkUser = client.getUser(clerkUsers.getFirst().getId());
        System.out.println("ID: " + clerkUser.getId());
        System.out.println("NAME: " + clerkUser.getName());

        Integer userCount = client.countUsers().getCount();
        System.out.println("USER COUNT: " + userCount);


        System.out.println("End");

    }
}
