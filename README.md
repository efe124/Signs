# Signs
You can simply sign any item you want in the game!

## Installation
1. Install the jar.
2. Put it to the folder called 'plugins' in your server.
3. Start the server
4. Have fun paying all your fortune for a stick signed by Dream!

## Configuration
**config.yml** has these two options:

| Name             | Description                                | Type                                                                               |
|------------------|--------------------------------------------|------------------------------------------------------------------------------------|
| `blocked-items`  | Items that players will be unable to sign. | [Material](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html) list |
| `enchanted-sign` | Makes signed items glow like enchanted.    | [Boolean](https://docs.oracle.com/javase/8/docs/api/java/lang/Boolean.html)        |


**lang.yml**
````yaml
signed-by: '&7Signed by &b%player%' # Seen at the bottom of items when they got signed by someone
signed: '&aSuccessfully signed this item!' # When someone uses /sign
no-item: '&cThere is nothing in your hand' # When someone uses /sign but there is nothing in their main hand.
not-signable: '&cItem in your hand is blocked for signing' # When someone tries to sign a blocked item.
already-signed: '&cSomeone called &b%player%&c already signed this item.' # When someone tries to sign an item that has already been signed.
````