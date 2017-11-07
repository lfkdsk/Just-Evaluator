package com.lfkdsk.justel.utils.test;

import com.lfkdsk.justel.utils.test.goods.Item;
import com.lfkdsk.justel.utils.test.user.Buyer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhulin(fengyuan)
 * @since 17/7/27.
 */
public class OrderLineSpec implements IFact {

    private Buyer buyer = new Buyer() {

        @Override
        public long getUserTag() {
            return 0;
        }

        @Override
        public long getUserTag2() {
            return 0;
        }

        @Override
        public long getUserTag3() {
            return 0;
        }

        @Override
        public long getUserTag4() {
            return 0;
        }

        @Override
        public long getUserTag5() {
            return 0;
        }

        @Override
        public long getUserTag6() {
            return 0;
        }

        @Override
        public long getUserTag7() {
            return 0;
        }

        @Override
        public long getUserTag8() {
            return 0;
        }

        @Override
        public long getUserTag9() {
            return 0;
        }

        @Override
        public long getUserTag10() {
            return 0;
        }

        @Override
        public long getUserTag11() {
            return 0;
        }

        @Override
        public long getUserTag12() {
            return 0;
        }

        @Override
        public long getUserTag13() {
            return 0;
        }

        @Override
        public long getUserTag14() {
            return 0;
        }

        @Override
        public long getUserTag15() {
            return 0;
        }

        @Override
        public long getUserTag16() {
            return 0;
        }

        @Override
        public long getUserTag17() {
            return 0;
        }

        @Override
        public long getUserTag18() {
            return 0;
        }

        @Override
        public long getUserTag19() {
            return 0;
        }

        @Override
        public long getUserTag20() {
            return 0;
        }
    };

    private Item item = new Item() {
        @Override
        public Set<Integer> getItemTags() {
            Set<Integer> tags = new HashSet<>();
            tags.add(12345);
            tags.add(67890);
            return tags;
        }

        @Override
        public Long getItemOptions() {
            return null;
        }

        @Override
        public Map<String, String> getItemFeatures() {
            Map<String, String> features = new HashMap<>();
            features.put("zhulin", "111");
            features.put("fengyuan", "222");
            return features;
        }

        @Override
        public String getAuctionType() {
            return null;
        }

        @Override
        public Long getItemId() {
            return null;
        }

        @Override
        public Integer getStuffStatus() {
            return null;
        }
    };

    @Override
    public LatticeIdentityModel getStandardModel() {
        return new LatticeIdentityModel(this);
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Item getItem() {
        return item;
    }
}
