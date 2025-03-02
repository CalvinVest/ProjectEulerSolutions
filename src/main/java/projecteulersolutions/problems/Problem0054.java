package main.java.projecteulersolutions.problems;

import main.java.projecteulersolutions.EulerIO;
import main.java.projecteulersolutions.EulerUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Problem0054 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        System.out.println("This problem has not been solved.");

        File file = new File(EulerUtils.DATA_FILEPATH + "problem0054.txt");
        List<String> lines = EulerIO.readLinesFromFile(file);

        int countP1Wins = 0;

        for (String line : lines) {
            String[] hands = line.split(" ");
            String[] p1Hand = Arrays.copyOfRange(hands, 0, 5);
            String[] p2Hand = Arrays.copyOfRange(hands, 5, 10);

            HandResult p1Result = PokerHandEvaluator.evaluateHandRank(p1Hand);
            HandResult p2Result = PokerHandEvaluator.evaluateHandRank(p2Hand);

            System.out.println("\nPlayer 1's hand: " + Arrays.toString(p1Hand));
            System.out.println("Player 2's hand: " + Arrays.toString(p2Hand));
            System.out.println("Player 1 Hand Result: " + PokerHandEvaluator.handRankNames.get(p1Result.handRank));
            System.out.println("Player 2 Hand Result: " + PokerHandEvaluator.handRankNames.get(p2Result.handRank));

            // Compare hand ranks
            if (p1Result.handRank > p2Result.handRank) {
                countP1Wins++;
                System.out.println("Player 1 wins with " + PokerHandEvaluator.handRankNames.get(p1Result.handRank));
            } else if (p1Result.handRank < p2Result.handRank) {
                System.out.println("Player 2 wins with " + PokerHandEvaluator.handRankNames.get(p2Result.handRank));
            } else { // Same hand rank, need to break the tie
                int comparison = compareHands(p1Result, p2Result);
                if (comparison > 0) {
                    countP1Wins++;
                    System.out.println("Player 1 wins with a better " + PokerHandEvaluator.handRankNames.get(p1Result.handRank));
                } else if (comparison < 0) {
                    System.out.println("Player 2 wins with a better " + PokerHandEvaluator.handRankNames.get(p2Result.handRank));
                } else {
                    System.out.println("It's a tie!");
                }
            }

        }
        System.out.println("\nPlayer 1 wins " + countP1Wins + " hands.");
    }

    private static class HandResult {

        boolean found;
        int handRank;
        List<Integer> importantRanks; // Used for detailed comparison, e.g., for two pairs: both pairs' ranks and kicker

        HandResult(boolean found, int handRank, List<Integer> importantRanks) {
            this.found = found;
            this.handRank = handRank;
            this.importantRanks = importantRanks;
        }
    }

    private static int compareHands(HandResult p1Result, HandResult p2Result) {
        // Compare each card rank in the hands, starting from the highest-ranked card
        for (int i = 0; i < p1Result.importantRanks.size(); i++) {
            int p1Rank = p1Result.importantRanks.get(i);
            int p2Rank = p2Result.importantRanks.get(i);
            if (p1Rank > p2Rank) {
                return 1; // p1 wins this comparison
            } else if (p1Rank < p2Rank) {
                return -1; // p2 wins this comparison
            }
            // If ranks are equal, continue to the next card
        }
        return 0; // The hands are completely tied
    }

    private static class PokerHandEvaluator {
        // Map to translate card rank characters to numerical values for easier comparison

        private static final Map<Character, Integer> cardRankValues = Map.ofEntries(
                new SimpleEntry<>('2', 2),
                new SimpleEntry<>('3', 3),
                new SimpleEntry<>('4', 4),
                new SimpleEntry<>('5', 5),
                new SimpleEntry<>('6', 6),
                new SimpleEntry<>('7', 7),
                new SimpleEntry<>('8', 8),
                new SimpleEntry<>('9', 9),
                new SimpleEntry<>('T', 10),
                new SimpleEntry<>('J', 11),
                new SimpleEntry<>('Q', 12),
                new SimpleEntry<>('K', 13),
                new SimpleEntry<>('A', 14)
        );

        private static final Map<Integer, String> handRankNames = Map.of(
                1, "High Card",
                2, "One Pair",
                3, "Two Pair",
                4, "Three of a Kind",
                5, "Straight",
                6, "Flush",
                7, "Full House",
                8, "Four of a Kind",
                9, "Straight Flush",
                10, "Royal Flush"
        );

        // Utility method to get the numerical value of a card's rank
        private static int cardValue(String card) {
            return cardRankValues.get(card.charAt(0));
        }

        // Sorts the hand by card rank
        private static void sortHandByRank(String[] hand) {
            Arrays.sort(hand, Comparator.comparingInt(PokerHandEvaluator::cardValue));
        }

        // Counts the occurrences of each rank in the hand
        private static Map<Integer, Integer> rankOccurrences(String[] hand) {
            Map<Integer, Integer> occurrences = new HashMap<>();
            for (String card : hand) {
                int value = cardValue(card);
                occurrences.put(value, occurrences.getOrDefault(value, 0) + 1);
            }
            return occurrences;
        }

        public static HandResult evaluateHandRank(String[] hand) {
            sortHandByRank(hand);
            HandResult result;

            // Check for Royal Flush (highest)
            if ((result = isRoyalFlush(hand)).found) {
                return new HandResult(true, 10, result.importantRanks);
            }

            // Check for Straight Flush
            if ((result = isStraightFlush(hand)).found) {
                return new HandResult(true, 9, result.importantRanks);
            }

            // Check for Four of a Kind
            if ((result = isFourOfAKind(hand)).found) {
                return new HandResult(true, 8, result.importantRanks);
            }

            // Check for Full House
            if ((result = isFullHouse(hand)).found) {
                return new HandResult(true, 7, result.importantRanks);
            }

            // Check for Flush
            if ((result = isFlush(hand)).found) {
                return new HandResult(true, 6, result.importantRanks);
            }

            // Check for Straight
            if ((result = isStraight(hand)).found) {
                return new HandResult(true, 5, result.importantRanks);
            }

            // Check for Three of a Kind
            if ((result = isThreeOfAKind(hand)).found) {
                return new HandResult(true, 4, result.importantRanks);
            }

            // Check for Two Pairs
            if ((result = isTwoPairs(hand)).found) {
                return new HandResult(true, 3, result.importantRanks);
            }
            // Check for One Pair
            if ((result = isOnePair(hand)).found) {
                return new HandResult(true, 2, result.importantRanks);
            }

            // If none of the above, evaluate High Card (lowest)
            result = isHighCard(hand);
            return new HandResult(true, 1, result.importantRanks);
        }

        private static HandResult isRoyalFlush(String[] hand) {
            if (isStraightFlush(hand).found && cardValue(hand[0]) == 10) {
                // If it's a straight flush starting with a 10, it's a Royal Flush
                return new HandResult(true, 1, Collections.emptyList());
            }
            return new HandResult(false, -1, Collections.emptyList());
        }

        private static HandResult isStraightFlush(String[] hand) {
            if (isStraight(hand).found && isFlush(hand).found) {
                // If it's a straight and a flush, return the rank of the highest card for comparison
                return new HandResult(true, 9, Collections.singletonList(cardValue(hand[4])));
            }
            return new HandResult(false, -1, Collections.emptyList());
        }

        private static HandResult isFourOfAKind(String[] hand) {
            Map<Integer, Integer> occurrences = rankOccurrences(hand);
            for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
                if (entry.getValue() == 4) {
                    return new HandResult(true, 8, Collections.singletonList(entry.getKey()));
                }
            }
            return new HandResult(false, -1, Collections.emptyList());
        }

        private static HandResult isFullHouse(String[] hand) {
            Map<Integer, Integer> occurrences = rankOccurrences(hand);
            List<Integer> ranks = new ArrayList<>();
            int threeOfAKindRank = -1;
            int pairRank = -1;
            for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
                if (entry.getValue() == 3) {
                    threeOfAKindRank = entry.getKey();
                } else if (entry.getValue() == 2) {
                    pairRank = entry.getKey();
                }
            }
            if (threeOfAKindRank != -1 && pairRank != -1) {
                ranks.add(threeOfAKindRank);
                ranks.add(pairRank);
                return new HandResult(true, 7, ranks);
            }
            return new HandResult(false, -1, Collections.emptyList());
        }

        private static HandResult isFlush(String[] hand) {
            char suit = hand[0].charAt(1); // Get the suit of the first card
            if (Arrays.stream(hand).allMatch(card -> card.charAt(1) == suit)) {
                // Hand is a flush, return the ranks of all cards for comparison
                List<Integer> ranks = Arrays.stream(hand)
                        .map(card -> cardValue(card.charAt(0) + ""))
                        .collect(Collectors.toList());
                return new HandResult(true, 6, ranks);
            }
            return new HandResult(false, -1, Collections.emptyList());
        }

        private static HandResult isStraight(String[] hand) {
            sortHandByRank(hand); // Assuming hand is sorted
            for (int i = 0; i < 4; i++) {
                if (cardValue(hand[i + 1]) - cardValue(hand[i]) != 1) {
                    return new HandResult(false, -1, Collections.emptyList());
                }
            }
            // The rank of the highest card in the straight is sufficient for comparison
            return new HandResult(true, 5, Collections.singletonList(cardValue(hand[4])));
        }

        private static HandResult isThreeOfAKind(String[] hand) {
            Map<Integer, Integer> occurrences = rankOccurrences(hand);
            for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
                if (entry.getValue() == 3) {
                    return new HandResult(true, 4, Collections.singletonList(entry.getKey()));
                }
            }
            return new HandResult(false, -1, Collections.emptyList());
        }

        private static HandResult isTwoPairs(String[] hand) {
            Map<Integer, Integer> occurrences = rankOccurrences(hand);
            List<Integer> pairs = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
                if (entry.getValue() == 2) {
                    pairs.add(entry.getKey());
                }
            }
            if (pairs.size() == 2) {
                Collections.sort(pairs); // Ensure the order is correct for comparison
                int kicker = occurrences.entrySet().stream()
                        .filter(entry -> entry.getValue() == 1)
                        .map(Map.Entry::getKey)
                        .max(Integer::compare).orElse(-1);
                pairs.add(kicker); // Add kicker for complete comparison
                return new HandResult(true, 3, pairs);
            }
            return new HandResult(false, -1, Collections.emptyList());
        }

        private static HandResult isOnePair(String[] hand) {
            Map<Integer, Integer> occurrences = rankOccurrences(hand);
            for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
                if (entry.getValue() == 2) {
                    return new HandResult(true, 9, Collections.singletonList(entry.getKey()));
                }
            }
            return new HandResult(false, -1, Collections.emptyList());
        }

        private static HandResult isHighCard(String[] hand) {

            // Get the rank of the highest card in the hand
            int highestRank = cardValue(hand[hand.length - 1].substring(0, 1));

            // Return the rank of the highest card for comparison with the lowest hand rank
            return new HandResult(true, 1, Collections.singletonList(highestRank));
        }
    }
}
