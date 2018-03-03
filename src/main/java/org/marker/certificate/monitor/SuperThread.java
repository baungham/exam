package org.marker.certificate.monitor;

/**
 * 超级线程，支持暂停与恢复
 * 
 * @author marker
 * @version 1.0
 */
public abstract class SuperThread extends Thread {
	
	
	private boolean suspend = false;

	private Object control = new Object(); // 只是需要一个对象而已，这个对象没有实际意义

	public void setSuspend(boolean suspend) {
		if (!suspend) {
			synchronized (control) {
				control.notifyAll();
			}
		}
		this.suspend = suspend;
	}

	public boolean isSuspend() {
		return this.suspend;
	}

	public void run() {
		while (true) {  
			synchronized (control) {  
				if (suspend) {  
		             try {  
		                  control.wait();  
		            } catch (InterruptedException e) {   }  
	        	}  
			}  
			goRun();
		}
	}
	 protected abstract void goRun();
	 
	  

}
