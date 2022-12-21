# SUPEPORTAL
A portal plugin based on WorldGurad regions. PlaceholderAPI support. By TUCAOEVER.

# Commands:
```
/sportal create [PortalID] - Create a portal.  
/sportal delete [PortalID] - Delete a portal.  
/sportal set [PortalID] location - Set location of a portal.  
/sportal set [PortalID] region [RegionName] - Set portal trigger region.  
/sportal list - List all portals  
/sportal reload - Reload all data and configs  
```
# Permissions:
```
superportal.admin - all command use.  
superportal.create - use create command  
superportal.delete - use delete command  
superportal.set - use set command  
superportal.list - use list command  
superportal.reload-  - use reload command  
```
# Config:
language: cn  

# RegionConfig:
Format:  
```
PortalID:
  region: RegionName  
  location: Location  
  conditon: Condition  
```
Example:
```
test:
  region: TestRegion
  location:
    ==: org.bukkit.Location
    world: world
    x: -139.0
    y: 124.0
    z: 118.0
    pitch: 13.0
    yaw: -354.0
  condition: '"%player_name%" == TUCAOEVER && "%player_name%" != "tucao" || 1 >= 1'
```
