class StrictPhilosopher extends Philosopher
{
	StrictPhilosopher(final int number)
	{
		super(number);
	}

	
	// Think: We have throwed InterruptedException, so method may abnormal return.
	//   Can we guarantee safety if we needn't respond to controller after interruption.

	@Override
	protected synchronized boolean letMeGo(final Place place) throws InterruptedException
	{
		final boolean result = super.letMeGo(place);
		// TODO waiting use condition variable methods
		if (result) {
			while (whereAmI() != place) {
				this.wait();
			}
		}
		return result;
	}

	@Override
	protected synchronized void goWhereToGo()
	{
		super.goWhereToGo();
		// TODO awake others who're waiting this call
        this.notifyAll();
	}
	
	// Answer? Here's a hint that InterruptedException are only raised at specific times.
	//   Just like, wait() or place.appoint(this).
}
