package com.betterup.codingexercise.models.databasemodels;

/**
 * This enumerator class helps to define a clean list of constants that are utilized by the {@link com.betterup.codingexercise.daos.RealmAbstractDAO} class when it comes
 * to searching for items within the database that have common field names which are typically primary keys (uuid).
 */
public enum DomainModelFieldNames {
    DocId("uuid");

    private String value;

    private DomainModelFieldNames(final String value) {
        this.value = value;
    }

    public DomainModelFieldNames getTypeFromValue(final String fieldName) {
        for (DomainModelFieldNames type : DomainModelFieldNames.values()) {
            if (type.value.equals(fieldName)) {
                return type;
            }
        }

        return null;
    }

    public String getStringValue() {
        return value;
    }
}

