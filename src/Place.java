class Anywhere extends Place
{
	Anywhere()
	{
		super("anywhere");
	}

	@Override
	Room fromRoom()
	{
		return null;
	}
}

abstract class Place
{
	private final String placeName;
	private Philosopher appointed = null, whoIsHere = null;

	Place(final String name)
	{
		placeName = name;
	}

	@Override
	public String toString()
	{
		return placeName;
	}

	abstract Room fromRoom();

	/**
	 * Appoint and reserve this place for Philosopher. This function allows failure
	 * of appointment.
	 */
	public synchronized final boolean appoint(final Philosopher p) throws InterruptedException
	{
		if (appointed != null) // If some one was appointed here, method fails.
			return false;
		while (whoIsHere != null) // While some one's staying here, wait till it getOut().
			wait();
		appointed = p; // Appoint p to come here. So let appointed = p.
		return true;
	}

	/**
	 * Disappoint the inner Philosopher.
	 */
	public synchronized final boolean disappoint() throws InterruptedException
	{
		if (appointed == null) // If no one was appointed here, raise exception.
			throw new IllegalStateException("No one is appointed to " + this);
		while (whoIsHere == null) // While no one is here, wait till it getIn().
			wait();
		appointed = null; // Disappoint. So let appointed = null.
		return true;
	}

	/**
	 * Analog entry of appointed Philosopher.
	 */
	public synchronized final boolean getIn(final Philosopher p)
	{
		if (p != appointed) // If p isn't appointed, raise error.
			throw new IllegalStateException(p + " isn't appointed to get in " + this);
		whoIsHere = p;
		notifyAll();
		return true;
	}

	/**
	 * Analog departure of Philosopher.
	 */
	public synchronized final boolean getOut(final Philosopher p)
	{
		if (appointed != null) // If p isn't disappointed, raise error.
			throw new IllegalStateException(p + " isn't appointed to get out " + this);
		whoIsHere = null;
		notifyAll();
		return true;
	}

	public synchronized Philosopher whoIsAppointed()
	{
		return appointed;
	}

	public synchronized Philosopher whoIsHere()
	{
		return whoIsHere;
	}
}
