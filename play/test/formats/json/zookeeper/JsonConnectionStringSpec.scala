/*
 * Copyright (C) 2019  Ľuboš Kozmon <https://www.elkozmon.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package api.formats.json.zookeeper

import org.scalatest.FlatSpec
import play.api.libs.json._
import zookeeper.ConnectionString

class JsonConnectionStringSpec extends FlatSpec with JsonConnectionString {

  "Serialized JsonConnectionString" should "be a string" in {
    val j = ConnectionString("localhost:2181")
    val s = implicitly[Writes[j.type]].writes(j)

    assertResult(JsString("localhost:2181"))(s)
  }

  "JsonConnectionString" should "deserialize simple connection string" in {
    val s = """"localhost:2181""""
    val j = implicitly[Reads[ConnectionString]].reads(Json.parse(s))

    assert(j.isSuccess)
  }
}