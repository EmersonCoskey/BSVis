package net.emersoncoskey.bsvis.data.mapjson

case class Info(_version                     : String,
                _songName                    : String,
                _songSubName                 : String,
                _songAuthorName              : String,
                _levelAuthorName             : String,
                _beatsPerMinute              : Double,
                _shuffle                     : Double,
                _shufflePeriod               : Double,
                _previewStartTime            : Double,
                _previewDuration             : Double,
                _songFilename                : String,
                _coverImageFilename          : String,
                _environmentName             : String,
                _allDirectionsEnvironmentName: String,
                _songTimeOffset              : Double,
                /*_customData                  : Any,*/
                _difficultyBeatmapSets       : List[DifficultyBeatmapSet])
