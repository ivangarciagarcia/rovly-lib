package dev.ivangg.rovly.lib.clerk;

import dev.ivangg.rovly.lib.clerk.model.ClerkUser;

import java.io.IOException;
import java.util.List;

public class ClerkApiClientTester {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting up");

        ClerkApiClient client = new ClerkApiClient("Secret Key");

        List<ClerkUser> clerkUsers = client.listUsers(null);
        System.out.println("ID: " + clerkUsers.getFirst().getId());
        System.out.println("NAME: " + clerkUsers.getFirst().getName());

        System.out.println("End");

    }
}
