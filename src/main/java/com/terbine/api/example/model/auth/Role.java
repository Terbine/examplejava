/**
 * @preserve Copyright (c) 2021 TERBINE Inc. as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of Terbine Inc.
 * <p>
 * This material also contains proprietary and confidential information
 * of Terbine Inc. and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of Terbine Inc.
 */
package com.terbine.api.example.model.auth;


import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.ToString;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author brianeno.
 */
@ToString(includeFieldNames = true)
@JsonRootName("userRole")
public class Role {

    @Getter
    private Integer id;

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private int enabled = 1;

    public Role() {
    }

    public Role(final Integer id, String name, String description, int enabled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (!this.id.equals(role.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public enum Type {
        ADMIN(1) {
            @Override
            public String toString() {
                return "Administrator";
            }
        },
        DEVELOPER(2) {
            @Override
            public String toString() {
                return "Developer";
            }
        },
        FINANCE(3) {
            @Override
            public String toString() {
                return "Finance";
            }
        },
        LEGAL(4) {
            @Override
            public String toString() {
                return "Legal";
            }
        },
        OPERATOR(5) {
            @Override
            public String toString() {
                return "Operator";
            }
        },
        SUPERADMIN(6) {
            @Override
            public String toString() {
                return "Super Admin";
            }
        },
        BDEADMIN(7) {
            @Override
            public String toString() {
                return "BDE Admin";
            }
        },
        DATATHON(8) {
            @Override
            public String toString() {
                return "Datathon";
            }
        };

        private static final Map<Integer, Type> LOOKUP = new HashMap();

        static {
            Iterator var0 = EnumSet.allOf(Type.class).iterator();

            while (var0.hasNext()) {
                Type s = (Type) var0.next();
                LOOKUP.put(Integer.valueOf(s.getValue()), s);
            }
        }

        private int value;

        Type(int value) {
            this.value = value;
        }

        public static Type get(Integer value) {
            if (LOOKUP.containsKey(value)) {
                return (Type) LOOKUP.get(value);
            } else {
                throw new IllegalArgumentException("Invalid User role of " + value + " provided");
            }
        }

        public int getValue() {
            return this.value;
        }
    }
}
