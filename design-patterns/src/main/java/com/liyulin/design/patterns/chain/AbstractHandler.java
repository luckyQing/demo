package com.liyulin.design.patterns.chain;

public abstract class AbstractHandler {

	protected AbstractHandler nextHandler;

	public void next(AbstractHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public void doNextHandler(LoginReqVO loginReqVO) {
		if(nextHandler!=null) {
			nextHandler.doHandler(loginReqVO);
		}
	}

	public abstract void doHandler(LoginReqVO loginReqVO);

	public static class Builder {
		private AbstractHandler head;
		private AbstractHandler tail;

		public Builder addHandler(AbstractHandler handler) {
			if (this.head == null) {
				this.head = this.tail = handler;
				return this;
			}
			this.tail.next(handler);
			this.tail = handler;
			return this;
		}

		public AbstractHandler build() {
			return this.head;
		}
	}

}