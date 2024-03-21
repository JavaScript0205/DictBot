package map;

import java.util.StringJoiner;

public class MapUz<K, V> {
    EntryUz<K, V>[] table;
    private final int DEFAULT_CAPACITY = 10;

    public MapUz() {
        table = new EntryUz[DEFAULT_CAPACITY];
    }

    public MapUz(int size) {
        table = new EntryUz[size];
    }

    public boolean put(K key, V value) {
        EntryUz<K, V> newEntry = new EntryUz<>(key, value);

        int hash = key.hashCode();
        int index = hash % table.length;

        if (table[index] == null) {
            table[index] = newEntry;
            return true;
        }

        EntryUz<K, V> head = table[index];

        while (head != null) {
            if (head.getKey().equals(key)) {
                head.setValue(value);
                return true;
            }

            head = head.next;
        }

        head = newEntry;

        return true;
    }

    public V get(K key) {
        int hash = key.hashCode();
        int index = hash % table.length;

        EntryUz<K,V> head=table[index];

        while(head!=null){
            if(head.getKey().equals(key)){
                return head.getValue();
            }
            head=head.next;

        }

        return null;
    }

    public boolean containsKey(K key) {
        int hash = key.hashCode();
        hash = hash > 0 ? hash : hash * -1;
        int index = hash % table.length;

        EntryUz<K, V> head = table[index];

        while(head != null) {
            if (head.getKey().equals(key)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public boolean containsValue(V value) {

        for (EntryUz<K, V> entry : table) {
            EntryUz<K, V> head = entry;

            while(head != null) {
                if (head.getValue().equals(value)) {
                    return true;
                }
                head = head.next;
            }
        }

        return false;
    }

    public boolean remove(K key) {
        int hash = key.hashCode();
        hash = hash > 0 ? hash : hash * -1;
        int index = hash % table.length;

        EntryUz<K, V> head = table[index];
        EntryUz<K, V> pr = table[index];


        if (head.getKey().equals(key)) {
            table[index] = head.next;
            return true;
        }

        while(head != null) {
            if (head.getKey().equals(key)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    //TODO: remove, containsKey, containsValue
    //TODO: getOrDefault

    @Override
    public String toString() {
        StringJoiner joiner=new StringJoiner(", ","[","]");
        EntryUz<K,V> head;

        for (int i = 0; i < table.length; i++) {
            head=table[i];
            while(head!=null){
                joiner.add(head.getKey()+" : "+head.getValue());
                head=head.next;
            }
        }

        return joiner.toString();
    }

    static class EntryUz<K, V> {
        private K key;
        private V value;
        EntryUz<K, V> next;

        public EntryUz() {
        }

        public EntryUz(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + " : " + value;
        }
    }
}
