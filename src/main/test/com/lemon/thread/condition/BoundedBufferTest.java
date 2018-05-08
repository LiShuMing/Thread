package com.lemon.thread.condition;

import org.junit.Test;

/**
 * @author : lishuming
 */
public class BoundedBufferTest {
	@Test
	public void testBuffer() {
		BoundedBeffer buffer = new BoundedBeffer();
		try {
			for (int i = 0; i < 99; i++) {
				buffer.put((Object) "a");
				;
			}

			for (int i = 0; i < 99; i++) {
				Object x = buffer.take();
				assert x.equals("a");
			}
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}

	@Test
	public void testThreadBuffer() {
		BoundedBeffer buffer = new BoundedBeffer();

		new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					for (int i = 0; i < 101; i++) {
						buffer.put(new Integer(i));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 101; i++) {
						Integer r = (Integer) buffer.take();
						assert r == i;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
