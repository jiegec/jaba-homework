class Room
{
	private final int id;

	class Bed extends Place
	{
		Bed()
		{
			super("bed-" + id);
		}
		@Override
		Room fromRoom()
		{
			return Room.this;
		}
	}

	class Sofa extends Place
	{
		Sofa()
		{
			super("sofa-" + id);
		}
		@Override
		Room fromRoom()
		{
			return Room.this;
		}
	}

	final Bed bed;
	final Sofa sofa;

	Room(final int roomId)
	{
		id = roomId;
		bed = new Bed();
		sofa = new Sofa();
	}
	@Override
	public String toString()
	{
		return "Room " + id;
	}
}
