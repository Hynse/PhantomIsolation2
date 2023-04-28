PhantomIsolation is a plugin allow player choose phantom should spawn on them self.

# Key Features:

1.  In-game command to check, enabled, disabled setting.
     
2.  Permission-based access: Server administrators can control which players have access to the plugin's features by configuring permissions.
    
3. Configurable messages and settings
    
4. Support Mysql , Flat Flies storage type

> In summary, PhantomIsolation is help player don't like phantom still enjoy the game.

# Permissions & Commands
| Command | Description  | Permission | Default |
|--|--|--|--|
| `/phantomisolation` | usage command list | `greenchunk.use` | `true`|
| `/phantomisolation check` | check current settings | `greenchunk.use` | `true`|
| `/phantomisolation status` | check current settings | `greenchunk.use` | `true`|
| `/phantomisolation enable` | disable phantom to spawn on you | `greenchunk.use` | `true`|
| `/phantomisolation on` | disable phantom to spawn on you | `greenchunk.use` | `true`|
| `/phantomisolation disable` | enable phantom to spawn on you | `greenchunk.use` | `true`|
| `/phantomisolation off` | enable phantom to spawn on you | `greenchunk.use` | `true`|
| `/phantomisolationrelaod` | reload configuration flies | `greenchunk.reload ` | `false`|
| `/pilr` | reload configuration flies | `greenchunk.reload ` | `false`|
| `/pil <usage>` | alias command for easy to using command  | `greenchunk.use` | `true`|


# Config.yml
```yml
# PhantomIsolation2 Configuration

# Task settings
task:
  initial-delay-tick: 20
  period-tick: 20

# PhantomIsolation command messages
phantomisolation-command:
  messages:
    status: "&aPhantom Isolation is currently %status%"
    status_enabled: "&aenabled"
    status_disabled: "&cdisabled"
    not-player: "&cThis command can only be executed by a player!"
    enabled: "&aPhantom Isolation is now enabled."
    disable: "&cPhantom Isolation is now disabled."
    usage: "&6Usage: /phantomisolation &a[check|disable|enable]"

# PhantomIsolationReload command messages
phantomisolationreload-command:
  messages:
    reload-config: "&aConfiguration reloaded successfully."
    reload-config-error: "&cAn error occurred while reloading the configuration."

# Database settings and error messages
database:
  # Database type
  datatype: "flatfile" # Options: "flatfile" or "mysql"

  # MySQL database settings (only used if datatype is "mysql")
  address: "localhost:3306"
  user: "username"
  password: "password"
  database: "phantom_isolation"

  # Flat file error messages
  flatfile-messages:
    fail-load: "&cFailed to load data from the flat file."
    fail-save: "&cFailed to save data to the flat file."

  # MySQL error messages
  mysql-messages:
    fail-connect: "&cFailed to connect to MySQL database."
    fail-load: "&cFailed to load data from the MySQL database."
    fail-save: "&cFailed to save data to the MySQL database."
```
## Contributing

If you would like to contribute to PhantomIsolation, feel free to submit a pull request with your changes. All contributions are welcome and appreciated.

## License
This plugin is licensed under the MIT license. See [LICENSE.md](https://github.com/Hynse/PhantomIsolation2/blob/master/LICENSE.md) for more information.
