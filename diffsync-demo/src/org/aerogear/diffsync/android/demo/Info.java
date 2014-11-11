package org.aerogear.diffsync.android.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Info {
    
    private String name;
    private String profession;
    private List<Hobby> hobbies;
    
    public Info(final String name, final String profession, final Hobby... hobbies) {
        this.name = name;
        this.profession = profession;
        this.hobbies = new ArrayList<Hobby>(Arrays.asList(hobbies));
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public static class Hobby {

        private final String id;
        private final String description;

        public Hobby(final String id, final String description) {
            this.id = id;
            this.description = description;
        }

        public String id() {
            return id;
        }

        public String description() {
            return description;
        }

    }
}
