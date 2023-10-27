package com.dcp.portone.leet;

import java.util.Scanner;

public class AdventureGameDemo {
    public static void main(String[] args) {
        String myLocations = """
                lake,at the edge of the Tim,E:ocean,W:forest, S:well house, N:cave
                ocean,on Tim's beach before an angry sea,W:lake
                cave,at the mouth of Tim's bat cave,E:Ocean,W:forest,S:lake
                """;
        AdventureGame game = new AdventureGame(myLocations);

        game.play("lake");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String direction = scanner.nextLine().trim().toUpperCase().substring(0,1);
            if(direction.startsWith("Q") || direction.startsWith("q"))
                break;
            game.move(direction);
        }

        scanner.close();
    }
}
