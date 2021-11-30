package net.emersoncoskey.bsvis.data.mapjson

case class Difficulty(_version   : String,
                      _notes     : List[NoteJson],
                      _obstacles : List[WallJson],
                      _events    : List[LightJson],
                      _customData: Any)
