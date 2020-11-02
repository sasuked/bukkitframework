package io.github.sasuked.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class AbstractCommand extends Command {
  
  public AbstractCommand(String name) {
    super(name);
  }
  
  @Override
  public boolean execute(CommandSender commandSender, String s, String[] strings) {
    return perform(commandSender, strings);
  }
  
  protected abstract boolean perform(CommandSender commandSender, String... args);
  
  
  private boolean response(CommandSender commandSender, String... message) {
    commandSender.sendMessage(message);
    return false;
  }
  
  private boolean isInt(String label){
    try {
      Integer.parseInt(label);
    }catch (Exception e){
      return false;
    }
    
    return true;
  }
  
  public boolean isDouble(String label){
    try {
      Double.parseDouble(label);
    }catch (Exception e){
      return false;
    }
    
    return true;
  }
}
