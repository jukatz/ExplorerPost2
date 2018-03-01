package app.explorerpost2.exception;

import java.lang.Exception;

public class NotUpdatedException extends Exception
{
  public NotUpdatedException( String psError )
  {
  	super( psError );
  }
}
