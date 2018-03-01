package app.explorerpost2.exception;

import java.lang.Exception;

public class ProcessException extends Exception
{
  public ProcessException( String psError )
  {
  	super( psError );
  }
}
