package com.ellialti.earthQW.service;

import com.ellialti.earthQW.domain.account.Account;
import com.ellialti.earthQW.domain.Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    public AccountService() {
        CreateSampleAccountList(400);
    }

    private List<Account> sampleAccountData = new ArrayList<>();
    private static final String[] NAMES = {"John", "Jane", "James", "Jessica", "Michael", "Emily", "William", "Olivia", "Alexander", "Ava"};
    private static final String[] SURNAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};
    private static final String[] ADDRESSES = {"10 Main St", "20 Second Ave", "30 Third St", "40 Fourth Ave", "50 Fifth St", "60 Sixth Ave", "70 Seventh St", "80 Eighth Ave", "90 Ninth St", "100 Tenth Ave"};
    private static final double MIN_LATITUDE = 36.0;
    private static final double MAX_LATITUDE = 42.0;
    private static final double MIN_LONGITUDE = 26.0;
    private static final double MAX_LONGITUDE = 44.0;
    private static final Random RANDOM = new Random();

    private void CreateSampleAccountList(int length) {
        for (int i = 0; i < length; i++) {
            Account user = new Account();
            user.setName(getRandomName());
            user.setSurname(getRandomSurname());
            generateRandomCoordinateForUser(user);
            sampleAccountData.add(user);
        }
        System.out.println("Sample account list created with " + sampleAccountData.size() + " members ");
    }

    public Response GetAccount(String id) {
        // repository-> findAccountById-> return account.
        List<Account> filtered = sampleAccountData.stream().filter(x -> x.getId() == id).toList();
        return new Response(true, filtered.get(0));
    }

    public Response Login(String email, String password) {
        try {
            return new Response(true, "faKeToKen$:" + email + ":" + password);
        } catch (Exception e) {
            return new Response(true, false);
        }
    }

    public Response UpdateAccount(Account account) {
        // repository-> findAccountById-> , setNewValues-> return true.
        List<Account> filtered = sampleAccountData.stream().filter(x -> x.getId() == account.getId()).toList();
        if (filtered.isEmpty())
            return new Response(true, false);
        else {
            for (int i = 0; i < sampleAccountData.size(); i++) {
                if (sampleAccountData.get(i).getId() == filtered.get(0).getId()) {
                    sampleAccountData.set(i, filtered.get(0));
                }
            }
            return new Response(true, true);
        }
    }

    public Response UpdateAccountLocation(String id, double longitude, double latitude) {
        // repository-> findAccountById-> , setNewCoordinateValues-> return account.
        List<Account> filtered = sampleAccountData.stream().filter(x -> x.getId() == id).toList();
        filtered.get(0).SetLongitudeAndLatitude(longitude, latitude);
        return new Response(true, true);
    }

    public Response DeleteAccount(String id) {
        // repository-> findAccountById-> return account.
        List<Account> filtered = sampleAccountData.stream().filter(x -> x.getId() == id).toList();
        if (filtered != null) {
            sampleAccountData.remove(filtered.get(0));
            return new Response(true, true);
        } else {
            return new Response(true, false);
        }
    }

    public Response ListAccounts() {
        // repository-> findAccountById-> return account.
        return new Response(true, sampleAccountData);
    }

    public Response GetRelatives(String id) {
        // repository-> findAccountById-> , setNewValues-> return true.
        List<Account> filtered = sampleAccountData.stream().filter(x -> x.getId() == id).toList();
        if (filtered.isEmpty())
            return new Response(true, false);
        else {
            if (filtered.get(0).getRelativesIds().isEmpty()) {
                List emptyList = new ArrayList<Account>();
                return new Response(true, emptyList);
            } else {
                List<Account> relatives = new ArrayList<>();
                List<String> relativesIds = filtered.get(0).getRelativesIds();
                for (Account acc : sampleAccountData) {
                    if (relativesIds.contains(acc.getId())) {
                        relatives.add(acc);
                    }
                }
                return new Response(true, relatives);
            }
        }
    }

    public Response AddRelative(String id, String relativeId) {
        // repository-> findAccountById-> , setNewValues-> return true.
        List<Account> filtered = sampleAccountData.stream().filter(x -> x.getId() == id).toList();
        if (filtered.isEmpty())
            return new Response(true, false);
        else {
            int accIndex = -1;
            int relativeIndex = -1;
            for (int i = 0; i < sampleAccountData.size(); i++) {
                if (sampleAccountData.get(i).getId() == id) {
                    accIndex = i;
                    if (accIndex != -1 && relativeIndex != -1) break;
                } else if (sampleAccountData.get(i).getId() == relativeId) {
                    relativeIndex = i;
                    if (accIndex != -1 && relativeIndex != -1) break;
                }
            }
            filtered.get(0).getRelativesIds().add(sampleAccountData.get(relativeIndex).getId());
            sampleAccountData.set(accIndex, filtered.get(0));
            return new Response(true, true);
        }
    }

    public Response DeleteRelative(String id, String relativeId) {
        // repository-> findAccountById-> , setNewValues-> return true.
        List<Account> filtered = sampleAccountData.stream().filter(x -> x.getId() == id).toList();
        if (filtered.isEmpty())
            return new Response(true, false);
        else {
            int accIndex = sampleAccountData.indexOf(filtered.get(0));
            if (filtered.get(0).getRelativesIds().contains(relativeId))
                filtered.get(0).getRelativesIds().remove(relativeId);
            sampleAccountData.set(accIndex, filtered.get(0));
            return new Response(true, true);
        }
    }

    public Response FindAccountsWithinRadius(double longitude, double latitude, double radius) {
        List<Account> result = new ArrayList<>();
        for (Account user : sampleAccountData) {
            // latA, longA, latB, longB
            double d = greatCircleDistance(latitude, longitude, user.getCoordinate().getLatitude(), user.getCoordinate().getLongitude());
            if (d <= radius) {
                result.add(user);
            }
        }
        return new Response(true, result);
    }

    private static double greatCircleDistance(double lat1, double lng1, double lat2, double lng2) {
//        const dLat = (lat2 - lat1) * (Math.PI / 180);
//       const dLng = (lng2 - lng1) * (Math.PI / 180);
//  const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
//                Math.cos(lat1 * (Math.PI / 180)) * Math.cos(lat2 * (Math.PI / 180)) *
//                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
//  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//        return EARTH_RADIUS * c;
        double earthRadius = 6371; // Radius of the earth in km
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.cos(lng2 - lng1)) * earthRadius;
        return d;
    }

    private static String getRandomName() {
        return NAMES[RANDOM.nextInt(NAMES.length)];
    }

    private static String getRandomSurname() {
        return SURNAMES[RANDOM.nextInt(SURNAMES.length)];
    }

    private static String getRandomAddress() {
        return ADDRESSES[RANDOM.nextInt(ADDRESSES.length)];
    }

    private static void generateRandomCoordinateForUser(Account account) {
//        Random random = new Random();
        double longitude = MIN_LATITUDE + (MAX_LATITUDE - MIN_LATITUDE) * RANDOM.nextDouble();
        double latitude = MIN_LONGITUDE + (MAX_LONGITUDE - MIN_LONGITUDE) * RANDOM.nextDouble();
        account.getCoordinate().setLongitude(longitude);
        account.getCoordinate().setLatitude(latitude);
        System.out.println("Latitude: " + latitude + ", Longitude: " + longitude);
    }
}

