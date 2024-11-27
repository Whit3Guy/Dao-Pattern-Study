package entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {
    private int Id;
    private String Name;

    @Override
    public String toString() {
        return "Department{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Id == that.Id && Objects.equals(Name, that.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Name);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Department(int id, String name) {
        Id = id;
        Name = name;
    }
}
