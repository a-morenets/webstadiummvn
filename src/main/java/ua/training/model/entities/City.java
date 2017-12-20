package ua.training.model.entities;

/**
 * Created by Денис on 14.12.2016.
 */
public class City {
    private int id;
    private String name;
/*
    public static class Builder{
    	
    	private City city = new City();
    	
		public Builder setId(int id) {
			city.id = id;
			return this;
		}
		public Builder setName(String name) {
			city.name = name;
			return this;
		}
		public City build(){
			return city;
		}
        
    }
*/
    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (id != city.id) return false;
        return name != null ? name.equals(city.name) : city.name == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public static class Builder{
        City instance = new City();

        public Builder setName(String name) {
            instance.name = name;
            return this;
        }

        public Builder setName(String name , boolean isNull) {
            if(!isNull) {
                instance.name = name;
            }else{
                instance.name = null;
            }
            return this;
        }

        public Builder setId(int id) {
            instance.id = id;
            return this;
        }

        public City build() {
            return instance;
        }


    }
}
