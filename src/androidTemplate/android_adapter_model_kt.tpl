//
//  {{classname}}
//
//  Created by {{creater}} on {{time}}
//  Copyright (c) {{creater}} All rights reserved.


/**
   {{mark}}
*/

package {{package}};

import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
{{imports}}

class {{classname}} (context: Context, list: List<String>) : MAdapter<String>(context, list) {


    override fun getview(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        val item = get(position)
        if (convertView == null) {
            convertView = {{itemView}}(context)
        }
        (convertView as {{itemView}}).set(item)
        return convertView
    }
}

