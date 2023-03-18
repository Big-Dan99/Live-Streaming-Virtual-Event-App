package com.bigdan.exception;

public enum ExceptionConstant {

            SERVER_ERROR("server.error"),

       		USER_NOT_FOUND("user.not.found"),

			NOT_FOUND("not.found");




        		private final String type;

        		private ExceptionConstant(String type) {
        			this.type = type;
        		}

        		@Override
		public String toString() {
        			return this.type;
        		}


}
