/*
 * Copyright (C) 2023 Luke Bemish and contributors
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

ModsDotGroovy.make {
    modLoader = 'lowcodefml'
    loaderVersion = '[1,)'
    issueTrackerUrl = 'https://github.com/lukebemish/NoMorePortals/issues'
    license = 'LGPL-3.0-or-later'

    mod {
        modId = this.buildProperties.mod_id
        displayName = this.buildProperties.mod_name
        displayTest = DisplayTest.IGNORE_SERVER_VERSION
        version = this.version
        onQuilt {
            group = this.group
        }
        displayUrl = 'https://github.com/lukebemish/NoMorePortals'
        contact.sources = 'https://github.com/lukebemish/NoMorePortals'
        author 'Luke Bemish'
        description = "A mod for stopping players from using nether portals."

        dependencies {
            onForge {
                forge = ">=${this.forgeVersion}"
            }
            minecraft = ">=${this.libs.versions.minecraft}"
            onQuilt {
                quiltLoader = ">=${this.quiltLoaderVersion}"
            }
        }

        displayTest = 'IGNORE_SERVER_VERSION'
    }

    onQuilt {
        mixin = ['mixin.nomoreportals.json']
    }
}
