class Philosopher implements Runnable
{
	private final int myNum;

	Philosopher(final int number)
	{
		myNum = number;
	}

	@Override
	public final String toString()
	{
		return "Philosopher " + myNum;
	}

	// for philosopher control
	private Room shallInRoom;

	// control function, called by dispatcher
	final void getInspired(final Room room)
	{
		try {
			if (shallInRoom != null) {
				System.out.println("I'm " + this + ", don't move me.");
				return;
			}
			System.out.println("I'm " + this + ", I wanna think about philosophy at " + room + ".");
			if (goToRoom(room))
				shallInRoom = room;
			else
				System.out.println("I'm " + this + ", I was kept out of the door.");
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	// control function, called by dispatcher
	final void inspirationVanished()
	{
		try {
			letMeGo(new Anywhere());
			shallInRoom = null;
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Simulate function. Just go to room. We need this function in avoid of
	 * out-sync or deadlock.
	 */
	private synchronized final boolean goToRoom(final Room room) throws InterruptedException
	{
		for (;;) {
			final Place target;
			synchronized (room) {
				if (room.sofa.whoIsAppointed() != null)
					target = null;
				else if (room.bed.whoIsAppointed() != null)
					target = room.sofa;
				else
					target = room.bed;
			}
			if (target == null)
				return false;
			if (letMeGo(target))
				return true;
		}
	}

	// for philosopher simulation
	private volatile Place whereAmI = null, whereToGo = null;

	protected synchronized Place whereAmI()
	{
		return whereAmI;
	}

	protected synchronized Place whereToGo()
	{
		return whereToGo;
	}

	/**
	 * Simulate function. If argument place is reachable, appoint it and modify
	 * variable whereToGo.
	 * 
	 * @return whether the place is appointed
	 */
	protected synchronized boolean letMeGo(final Place place) throws InterruptedException
	{
		while (whereToGo != null)
			wait();
		Object lockRoom = place.fromRoom();
		if (lockRoom == null && whereAmI != null)
			lockRoom = whereAmI.fromRoom();
		if (lockRoom == null)
			lockRoom = this; // this is just Philosopher, this statement avoids synchronized(null)
		synchronized (lockRoom) {
			if (place.appoint(this)) {
				if (whereAmI != null)
					whereAmI.disappoint();
				whereToGo = place;
				notify();
			}
		}
		return whereToGo == place;
	}

	/**
	 * Simulate function. When variable whereToGo has a new value, fulfill it and
	 * modify variable whereAmI.
	 */
	protected synchronized void goWhereToGo()
	{
		if (whereToGo != null) {
			if (whereAmI != null) {
				whereAmI.getOut(this);
				if (whereAmI instanceof Room.Bed) {
					System.out.println("I'm " + this + ", I get up with philosophical thoughts.");
					final Room room = ((Room.Bed) whereAmI).fromRoom();
					final Philosopher sofaer;
					synchronized (room) {
						sofaer = room.sofa.whoIsAppointed();
					}
					if (sofaer != null)
						sofaer.sendMessage(Message.GOTO_BED);
				}
			}
			whereAmI = whereToGo;
			whereAmI.getIn(this);
			whereToGo = null;
			System.out.println("I'm " + this + ", I'm at " + whereAmI + ".");
			if (whereAmI instanceof Room.Bed)
				System.out.println("I'm " + this + ", I'm gonna think about philosophy.");
			notify();
		}
	}

	// for normal stop
	private volatile boolean interrupted = false, stopped = false;

	private synchronized final void still() throws InterruptedException
	{
		final Philosopher bedKeeper;
		if (interrupted && whereAmI instanceof Room.Sofa) {
			final Room room = ((Room.Sofa) whereAmI).fromRoom();
			synchronized (room) {
				bedKeeper = room.bed.whoIsAppointed();
			}
		} else
			bedKeeper = null;
		while (whereToGo == null && !isStopped()) {
			if (!interrupted)
				wait();
			else if ((bedKeeper == null || bedKeeper.isStopped()))
				stop();
			else
				wait(2);
		}
	}

	private synchronized final void checkMessage()
	{
		if (getMessage() == Message.GOTO_BED && whereToGo == null
				&& whereAmI instanceof Room.Sofa) {
			final Place target = ((Room.Sofa) whereAmI).fromRoom().bed;
			new Thread(() -> {
				try {
					letMeGo(target);
				} catch (InterruptedException e) {
				}
			}).start();
		}
	}

	private synchronized final void oneRound() throws InterruptedException
	{
		checkMessage();
		goWhereToGo();
		still();
	}

	@Override
	public final void run()
	{
		while (!isStopped()) {
			try {
				oneRound();
				// Think: How could we know the appointments are correct & robust?
				Thread.sleep(1);
				// Answer: checks whether tardy pigs can execute it.
			} catch (final InterruptedException e) {
				interrupted = true;
			}
		}
	}

	private synchronized final void stop()
	{
		stopped = true;
	}

	private synchronized final boolean isStopped()
	{
		return stopped;
	}

	private enum Message
	{
		NONE, GOTO_BED
	}

	private volatile Message message;

	private synchronized final void sendMessage(final Message msg)
	{
		message = msg;
		notify();
	}

	private synchronized final Message getMessage()
	{
		final Message r = message;
		message = null;
		return r != null ? r : Message.NONE;
	}
}
