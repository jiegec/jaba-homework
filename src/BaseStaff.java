abstract class BaseStaff {
    /**
     * Get the type of this staff.
     * @return a String, "homework7.Teacher" or "homework7.Student"
     */
    public abstract String getType();

    /**
     * Get the number of this staff.
     * @return an int, teacher number or student number
     */
    public abstract int getNumber();

    @Override
    public int hashCode() {
        return (getType() + getNumber()).hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof BaseStaff &&
                getType().equals(((BaseStaff) obj).getType()) &&
                getNumber() == ((BaseStaff) obj).getNumber();
    }
}
