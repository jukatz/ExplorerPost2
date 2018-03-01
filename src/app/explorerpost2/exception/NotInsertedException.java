package app.explorerpost2.exception;

import java.lang.Exception;

public class NotInsertedException extends Exception
{
  public NotInsertedException( String psError )
  {
  	super( psError );
  }
}
