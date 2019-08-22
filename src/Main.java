import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class MainPhilosopher
{
	private static Philosopher[] p;
	private static Thread[] thread;
	private static Room[] room;

	public static void main(final String[] args) throws FileNotFoundException
	{
		final Scanner cin = new Scanner(System.in);
		final int n = cin.nextInt(), m = cin.nextInt();
		init(n, m);
		while (cin.hasNext()) {
			final int condition = cin.nextInt();
			int i, j;
			if (condition == 0) {
				i = cin.nextInt();
				j = cin.nextInt();
				// Debug
				// System.out.println("! " + i + " -> " + j);
				p[i].getInspired(room[j]);
			} else {
				i = cin.nextInt();
				// Debug
				// System.out.println("! " + i);
				p[i].inspirationVanished();
			}
		}
		cin.close();
		halt();
	}

	private static void init(final int n, final int m)
	{
		System.out.println("<INIT>");
		p = new Philosopher[n];
		thread = new Thread[n];
		for (int i = 0; i != n; ++i) {
			p[i] = new StrictPhilosopher(i);
			thread[i] = new Thread(p[i], "philosopher_" + i);
			thread[i].setDaemon(true);
			thread[i].start();
			p[i].inspirationVanished();
		}
		room = new Room[m];
		for (int j = 0; j != m; ++j)
			room[j] = new Room(j);
	}

	private static void halt()
	{
		System.out.println("<INTERRUPT>");
		for (final Thread t : thread)
			t.interrupt();
		for (final Thread t : thread)
			try {
				t.join();
			} catch (final InterruptedException e) {
			}
		System.out.println("<HALT>");
	}
}
